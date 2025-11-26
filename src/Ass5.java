class Ass5 {
    private int bar = 0;

    public void baz() {
        synchronized(this) {
            bar++;
        }
    }

    public int getBar() {
        return bar;
    }

    public static void main(String[] args) throws InterruptedException {
        Ass5 a = new Ass5();

        Thread[] arr = new Thread[2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    a.baz();
                }
            });
            arr[i].start();
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i].join();
        }

        System.out.println(a.getBar());
    }
}
