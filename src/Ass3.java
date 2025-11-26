class Ass3 {
    private int bar = 0;

    Ass3() {
    }

    public void baz() {
        ++this.bar;
    }

    public int getBar() {
        return this.bar;
    }

    public static void main(String[] var0) {
        Ass3 var1 = new Ass3();
        Thread[] var2 = new Thread[2];

        for(int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = new Thread(() -> {
                for(int var1x = 0; var1x < 10000; ++var1x) {
                    var1.baz();
                }

            });
            var2[var3].start();
        }

        for(int var6 = 0; var6 < var2.length; ++var6) {
            try {
                var2[var6].join();
            } catch (InterruptedException var5) {
                var5.printStackTrace();
            }
        }

        System.out.println(var1.getBar());
    }
}