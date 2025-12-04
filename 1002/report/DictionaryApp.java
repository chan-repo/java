/*
 * 12. 다음은 '키'와 '값'의 두 문자열을 하나의 아이템으로 저장하는 추상 클래스 PairMap이다.
 * put()은 '키'와 '값'을 저장하고 get()은 '키'로 검색하여 '값'을 리턴하는 메소드이다.
 * PairMap을 상속받아 Dictionary 클래스를 궇ㄴ하고 이를 다음과 활용하는 main() 메소드를 가진 DictionaryApp 클래스로 작성하라.
 */

package Chapter5;

abstract class PairMap {
    protected String keyArray[]; // 키 문자열을 저장하는 배열
    protected String valueArray[]; // 값 문자열을 저장하는 배열
    
    abstract public String get(String key); // key 값으로 value 검색
    abstract public void put(String key, String value); // key와 value를 쌍으로 저장. key가 이미 저장되어 있으면 값을 value로 수정
    abstract public String delete(String key); // key 값을 가진 아이템(value와 함께) 삭제. 삭제된 value 값 리턴
    abstract public int length(); // 현재 저장된 아이템 개수 리턴
}

class Dictionary extends PairMap {
	private int size; // 현재 저장된 개수
	private int capacity; // 배열 최대 크기

    public Dictionary(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.keyArray = new String[capacity];
        this.valueArray = new String[capacity];
    }
    @Override
    public String get(String key) {
        for (int i = 0; i < size; i++) {
            if (keyArray[i].equals(key)) {
                return valueArray[i];
            }
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        for (int i = 0; i < size; i++) {
            if (keyArray[i].equals(key)) {
                valueArray[i] = value; // 키가 이미 있으면 값 수정
                return;
            }
        }
        if (size < capacity) {
            keyArray[size] = key;
            valueArray[size] = value;
            size++;
        }
    }

    @Override
    public String delete(String key) {
        for (int i = 0; i < size; i++) {
            if (keyArray[i].equals(key)) {
                String deletedValue = valueArray[i];
                // 배열을 재구성하여 삭제 처리
                for (int j = i; j < size - 1; j++) {
                    keyArray[j] = keyArray[j + 1];
                    valueArray[j] = valueArray[j + 1];
                }
                keyArray[size - 1] = null; // 마지막 원소 제거
                valueArray[size - 1] = null;
                size--;
                return deletedValue; // 삭제된 값 반환
            }
        }
        return null; // 키가 없으면 null 반환
    }

    @Override
    public int length() {
        return size;
    }
}

public class DictionaryApp {
	public static void main(String[] args) {
		Dictionary dic = new Dictionary(10);
		
		dic.put("황기태", "자바");
		dic.put("이재문", "파이썬");
		dic.put("이재문", "C++"); // 값 수정
		
		System.out.println("이재문의 값은 " + dic.get("이재문"));
		System.out.println("황기태의 값은 " + dic.get("황기태"));
		
		dic.delete("황기태");
		System.out.println("황기태의 값은 " + dic.get("황기태"));
	}
}
