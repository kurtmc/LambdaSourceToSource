public class Test1 {

    int x;

    public float a = 1;

    public float giveFloat();

    public Test1 test = new Test1();

    public Test1 method() {
        return null;
    }

    public void foo() {
        Test1 t = new Test1();
        x = 1;
        x = 3.42;
        x = 3.42f;
        x = method();
        x = new Test1();
        x = t.a;
        x = t.giveFloat();
        x = t.test.method();
        x = t.test.method().a;
        x = false;
        x = "Happy";
    }
}
