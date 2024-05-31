package com.library.algorithm;

/**
 * ממשק זה מגדיר את ה-API הכללי של משפחת האלגוריתמים לניהול הזמנות בספריה.
 */
public interface IAlgoBooking {
    void addBooking(String bookId);
    void removeBooking(String bookId);
    String getNextBooking();
}



/*
סיכום
IAlgoBooking: ממשק המגדיר את פעולות הבסיס לניהול הזמנות.
FCFSAlgorithm: מימוש אלגוריתם "First Come First Serve".
PriorityBookingAlgorithm: מימוש אלגוריתם המבוסס על תור עדיפויות.
BookingManager: מחלקה לניהול הזמנות באמצעות אלגוריתמים.
Book: מחלקה לייצוג ספר.
LibraryBookingApp: המחלקה הראשית של התוכנית שמבצעת את ההזמנות ומדפיסה את התוצאות.
BookingManagerTest: מחלקה לבדיקות יחידה של האלגוריתמים.
 */


/*
חלק א' – יצירת ממשק ושני מימושים
דרישות:

יצירת ממשק שמגדיר את ה-API הכללי של משפחת האלגוריתמים.
יצירת שתי מחלקות שמממשות את הממשק, כל אחת ע"פ לוגיקה שלה למימוש האלגוריתם.
 */