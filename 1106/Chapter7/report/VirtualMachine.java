/*
 * 12. 가상의 기계 명령으로 구성되 프로그램을 실행하는 가상 기계를 작성하라.
 * 명령 종류는 mov, add, sub, jn0, prt, exi의 6가지이다. mov sum 0은 sum 변수에 0을 저장하고,
 * add sum i는 sum 변수에 i 변수의 값을 더하고 add i 1은 변수 i를 1 증가시키며, sub n 1은 변수 n의
 * 값을 1 감소시키고, jn0 n 3은 변수 n의 값이 0과 같이 않으면 3번재 명령으로 점프하도록 처리하며,
 * prt sum은 sum 변수의 값을 화면에 출력하고, exi는 기계 명령으로 작성된 사용자 프로그램을 종료한다.
 * go는 사용자가 입력한 기계 명령 프로그램을 처음부터 실행하는 지시어이다.
 * prt와 exi를 제외한 명령들의 첫번째 피연산자는 항상 변수이고, 두 번째 피연산자는 변수이거나 숫자가 될 수 있다.
 */

package Practice;

import java.util.*;

public class VirtualMachine {
    private List<List<String>> program;  // 프로그램 저장
    private Map<String, Integer> variables;  // 변수 저장
    private int programCounter;  // 현재 실행 중인 명령어 위치

    public VirtualMachine() {
        this.program = new ArrayList<>();
        this.variables = new HashMap<>();
        this.programCounter = 0;
    }

    // 프로그램 라인 추가
    public void addProgramLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        if (!tokens.isEmpty()) {
            program.add(tokens);
        }
    }

    // 명령어 실행 메서드들
    private void executeMove(String var, String value) {
        int val;
        if (value.matches("-?\\d+")) {
            val = Integer.parseInt(value);
        } else {
            val = variables.getOrDefault(value, 0);
        }
        variables.put(var, val);
    }

    private void executeAdd(String var, String value) {
        int val;
        if (value.matches("-?\\d+")) {
            val = Integer.parseInt(value);
        } else {
            val = variables.getOrDefault(value, 0);
        }
        variables.put(var, variables.getOrDefault(var, 0) + val);
        // 디버깅용 로그
        //System.out.println("Adding " + val + " to " + var + ". New value: " + variables.get(var));
    }

    private void executeSub(String var, String value) {
        int val;
        if (value.matches("-?\\d+")) {
            val = Integer.parseInt(value);
        } else {
            val = variables.getOrDefault(value, 0);
        }
        variables.put(var, variables.getOrDefault(var, 0) - val);
    }

    private void executeJumpNotZero(String var, String lineNum) {
        if (variables.getOrDefault(var, 0) != 0) {
            // lineNum은 1부터 시작하는 line number를 가리키므로, 실제 program list index로 변환
            programCounter = Integer.parseInt(lineNum) - 1;
        }
    }

    private void executePrint(String var) {
        System.out.printf("[prt %s]에 의해 출력된 %s변수 값:%d%n", 
            var, var, variables.getOrDefault(var, 0));
    }

    // 프로그램 실행
    public void execute() {
        programCounter = 0;
        variables.clear();

        while (programCounter < program.size()) {
            List<String> instruction = program.get(programCounter);
            
            if (instruction.isEmpty()) {
                programCounter++;
                continue;
            }

            String cmd = instruction.get(0);
            
            switch (cmd) {
                case "mov":
                    executeMove(instruction.get(1), instruction.get(2));
                    break;
                case "add":
                    executeAdd(instruction.get(1), instruction.get(2));
                    break;
                case "sub":
                    executeSub(instruction.get(1), instruction.get(2));
                    break;
                case "jn0":
                    executeJumpNotZero(instruction.get(1), instruction.get(2));
                    break;
                case "prt":
                    executePrint(instruction.get(1));
                    break;
                case "exi":
                    printFinalState();
                    return;
            }
            programCounter++;
        }
        printFinalState();
    }

    // 최종 변수 상태 출력
    private void printFinalState() {
        System.out.println("프로그램 실행 종료. 변수들의 최종 값을 출력합니다.");
        StringJoiner sj = new StringJoiner(" ");
        List<String> sortedVars = new ArrayList<>(variables.keySet());
        Collections.sort(sortedVars);
        for (String key : sortedVars) {
            sj.add(key + ":" + variables.get(key));
        }
        System.out.println(sj.toString());
    }

    // 프로그램 초기화
    public void reset() {
        program.clear();
        variables.clear();
        programCounter = 0;
    }

    public static void main(String[] args) {
        VirtualMachine vm = new VirtualMachine();
        Scanner scanner = new Scanner(System.in);
        int lineNumber = 0;

        System.out.println("나의 가상 컴퓨터가 작동합니다. 프로그램을 입력해주세요. go를 입력하면 작동합니다.");

        while (true) {
            System.out.print(lineNumber + ">> ");
            String line = scanner.nextLine().trim();

            if (line.equals("go")) {
                vm.execute();
                vm.reset();
                lineNumber = 0;
                continue;
            }

            if (!line.isEmpty()) {
                vm.addProgramLine(line);
                lineNumber++;
            }
        }
    }
}
