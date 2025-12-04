public class TestHash {
    public static void main(String[] args) {
        String hash = util.PasswordUtil.hashPassword("admin1234");
        System.out.println(hash);
    }
}
