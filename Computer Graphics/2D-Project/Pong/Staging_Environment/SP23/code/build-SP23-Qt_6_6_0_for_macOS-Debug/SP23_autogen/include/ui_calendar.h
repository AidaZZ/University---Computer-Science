/********************************************************************************
** Form generated from reading UI file 'calendar.ui'
**
** Created by: Qt User Interface Compiler version 6.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CALENDAR_H
#define UI_CALENDAR_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Calendar
{
public:
    QMenuBar *menubar;
    QWidget *centralwidget;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Calendar)
    {
        if (Calendar->objectName().isEmpty())
            Calendar->setObjectName("Calendar");
        Calendar->resize(800, 600);
        menubar = new QMenuBar(Calendar);
        menubar->setObjectName("menubar");
        Calendar->setMenuBar(menubar);
        centralwidget = new QWidget(Calendar);
        centralwidget->setObjectName("centralwidget");
        Calendar->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(Calendar);
        statusbar->setObjectName("statusbar");
        Calendar->setStatusBar(statusbar);

        retranslateUi(Calendar);

        QMetaObject::connectSlotsByName(Calendar);
    } // setupUi

    void retranslateUi(QMainWindow *Calendar)
    {
        Calendar->setWindowTitle(QCoreApplication::translate("Calendar", "MainWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Calendar: public Ui_Calendar {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CALENDAR_H
