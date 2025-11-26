#include <iostream>
#include <thread>
#include <vector>
#include <chrono>   // לשימוש בהשהיה נכונה ב-C++11 ומעלה

using namespace std;

void foo(int a) {
    // השהיה קצרה (1 מילישנייה) שמגבירה את הסיכוי לבעיית מרוץ בפלט
    std::this_thread::sleep_for(std::chrono::milliseconds(1));

    // **הקטע הקריטי הלא-נעול:** תהליכונים רבים כותבים ל-cout בו זמנית
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