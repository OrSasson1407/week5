class Ass4 {
    private int bar = 0;

    // שינוי נדרש: הוספת synchronized למתודה baz()
    public synchronized void baz() {
        bar++;
    }

    // שינוי נדרש: הוספת synchronized למתודה getBar()
    public synchronized int getBar() {
        return bar;
    }

    public static void main(String[] args) throws InterruptedException {
        Ass4 f = new Ass4();
        Thread[] arr = new Thread[2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    f.baz();
                }
            });
            arr[i].start();
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i].join();
        }
        System.out.println(f.getBar());
    }
}