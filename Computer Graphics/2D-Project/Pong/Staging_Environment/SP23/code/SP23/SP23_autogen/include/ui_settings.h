/********************************************************************************
** Form generated from reading UI file 'settings.ui'
**
** Created by: Qt User Interface Compiler version 6.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SETTINGS_H
#define UI_SETTINGS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QScrollBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Settings
{
public:
    QWidget *centralwidget;
    QLabel *label;
    QLabel *label_2;
    QScrollBar *DarkMode;
    QLabel *label_3;
    QLabel *label_5;
    QPushButton *backButton;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Settings)
    {
        if (Settings->objectName().isEmpty())
            Settings->setObjectName("Settings");
        Settings->resize(800, 600);
        centralwidget = new QWidget(Settings);
        centralwidget->setObjectName("centralwidget");
        label = new QLabel(centralwidget);
        label->setObjectName("label");
        label->setGeometry(QRect(30, 40, 71, 41));
        label->setFrameShape(QFrame::Box);
        label->setFrameShadow(QFrame::Plain);
        label_2 = new QLabel(centralwidget);
        label_2->setObjectName("label_2");
        label_2->setGeometry(QRect(0, 100, 641, 31));
        QFont font;
        font.setFamilies({QString::fromUtf8("Tw Cen MT Condensed Extra Bold")});
        font.setPointSize(12);
        font.setBold(true);
        label_2->setFont(font);
        label_2->setFrameShape(QFrame::Box);
        label_2->setFrameShadow(QFrame::Plain);
        label_2->setLineWidth(1);
        label_2->setTextFormat(Qt::PlainText);
        DarkMode = new QScrollBar(centralwidget);
        DarkMode->setObjectName("DarkMode");
        DarkMode->setGeometry(QRect(110, 150, 71, 16));
        QFont font1;
        font1.setBold(true);
        font1.setStyleStrategy(QFont::PreferDefault);
        DarkMode->setFont(font1);
        DarkMode->setMouseTracking(false);
        DarkMode->setFocusPolicy(Qt::NoFocus);
        DarkMode->setOrientation(Qt::Horizontal);
        label_3 = new QLabel(centralwidget);
        label_3->setObjectName("label_3");
        label_3->setGeometry(QRect(30, 150, 71, 16));
        QFont font2;
        font2.setBold(true);
        label_3->setFont(font2);
        label_5 = new QLabel(centralwidget);
        label_5->setObjectName("label_5");
        label_5->setGeometry(QRect(40, 60, 49, 16));
        label_5->setFrameShape(QFrame::NoFrame);
        backButton = new QPushButton(centralwidget);
        backButton->setObjectName("backButton");
        backButton->setGeometry(QRect(530, 50, 80, 24));
        Settings->setCentralWidget(centralwidget);
        menubar = new QMenuBar(Settings);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 800, 21));
        Settings->setMenuBar(menubar);
        statusbar = new QStatusBar(Settings);
        statusbar->setObjectName("statusbar");
        Settings->setStatusBar(statusbar);

        retranslateUi(Settings);

        QMetaObject::connectSlotsByName(Settings);
    } // setupUi

    void retranslateUi(QMainWindow *Settings)
    {
        Settings->setWindowTitle(QCoreApplication::translate("Settings", "MainWindow", nullptr));
        label->setText(QCoreApplication::translate("Settings", "Scrummy", nullptr));
        label_2->setText(QCoreApplication::translate("Settings", "      Settings", nullptr));
        label_3->setText(QCoreApplication::translate("Settings", "Dark Mode", nullptr));
        label_5->setText(QCoreApplication::translate("Settings", "Logo", nullptr));
        backButton->setText(QCoreApplication::translate("Settings", "Back", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Settings: public Ui_Settings {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SETTINGS_H
