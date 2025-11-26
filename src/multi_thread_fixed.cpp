#include <iostream>
#include <thread>
#include <vector>
#include <chrono>
#include <mutex> // חובה עבור std::mutex ו-std::lock_guard

using namespace std;

// 1. הגדרת מוטקס גלובלי משותף (The Lock)
std::mutex cout_mutex;

void foo(int a) {
    std::this_thread::sleep_for(std::chrono::milliseconds(1));

    // 2. קטע קריטי - נעילה באמצעות lock_guard
    // lock_guard נועל את המוטקס בבנייה ומשחרר אותו אוטומטית ביציאה מהסקופ
    std::lock_guard<std::mutex> lock(cout_mutex);

    // פלט מסודר
    cout << "Thread ID: " << a << endl;
}

int main() {
    // יצירת 20 תהליכונים
    vector<thread> threads;
    for (int i = 0; i < 20; i++) {
        threads.emplace_back(foo, i);
    }

    // המתנה לסיום כל התהליכונים
    for (auto& t : threads) {
        t.join();
    }

    cout << "All threads finished." << endl;
    return 0;
}