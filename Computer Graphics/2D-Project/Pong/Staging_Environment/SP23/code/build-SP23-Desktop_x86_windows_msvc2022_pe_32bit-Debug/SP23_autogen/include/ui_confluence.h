/********************************************************************************
** Form generated from reading UI file 'confluence.ui'
**
** Created by: Qt User Interface Compiler version 6.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CONFLUENCE_H
#define UI_CONFLUENCE_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Confluence
{
public:
    QWidget *centralwidget;
    QTextBrowser *textBrowser;
    QPushButton *pushButton;
    QLabel *label;
    QLabel *label_2;
    QLabel *label_3;
    QPushButton *pushButton_2;
    QLabel *label_4;
    QLabel *label_5;
    QComboBox *comboBox;
    QLabel *label_6;
    QLabel *label_7;
    QLineEdit *lineEdit;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Confluence)
    {
        if (Confluence->objectName().isEmpty())
            Confluence->setObjectName("Confluence");
        Confluence->resize(800, 600);
        centralwidget = new QWidget(Confluence);
        centralwidget->setObjectName("centralwidget");
        textBrowser = new QTextBrowser(centralwidget);
        textBrowser->setObjectName("textBrowser");
        textBrowser->setGeometry(QRect(220, 160, 256, 192));
        pushButton = new QPushButton(centralwidget);
        pushButton->setObjectName("pushButton");
        pushButton->setGeometry(QRect(680, 40, 80, 24));
        label = new QLabel(centralwidget);
        label->setObjectName("label");
        label->setGeometry(QRect(0, 90, 751, 20));
        QFont font;
        font.setBold(true);
        label->setFont(font);
        label->setFrameShape(QFrame::Box);
        label->setLineWidth(2);
        label->setMidLineWidth(7);
        label_2 = new QLabel(centralwidget);
        label_2->setObjectName("label_2");
        label_2->setGeometry(QRect(0, 40, 81, 41));
        label_2->setFont(font);
        label_2->setFrameShape(QFrame::Box);
        label_2->setFrameShadow(QFrame::Plain);
        label_2->setLineWidth(3);
        label_3 = new QLabel(centralwidget);
        label_3->setObjectName("label_3");
        label_3->setGeometry(QRect(10, 60, 49, 16));
        pushButton_2 = new QPushButton(centralwidget);
        pushButton_2->setObjectName("pushButton_2");
        pushButton_2->setGeometry(QRect(690, 90, 61, 16));
        label_4 = new QLabel(centralwidget);
        label_4->setObjectName("label_4");
        label_4->setGeometry(QRect(10, 150, 91, 16));
        label_5 = new QLabel(centralwidget);
        label_5->setObjectName("label_5");
        label_5->setGeometry(QRect(20, 190, 141, 16));
        QFont font1;
        font1.setPointSize(15);
        font1.setBold(true);
        label_5->setFont(font1);
        comboBox = new QComboBox(centralwidget);
        comboBox->setObjectName("comboBox");
        comboBox->setGeometry(QRect(20, 210, 72, 24));
        label_6 = new QLabel(centralwidget);
        label_6->setObjectName("label_6");
        label_6->setGeometry(QRect(30, 350, 91, 16));
        QFont font2;
        font2.setPointSize(12);
        font2.setBold(true);
        label_6->setFont(font2);
        label_7 = new QLabel(centralwidget);
        label_7->setObjectName("label_7");
        label_7->setGeometry(QRect(30, 360, 121, 16));
        lineEdit = new QLineEdit(centralwidget);
        lineEdit->setObjectName("lineEdit");
        lineEdit->setGeometry(QRect(50, 380, 231, 24));
        Confluence->setCentralWidget(centralwidget);
        menubar = new QMenuBar(Confluence);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 800, 21));
        Confluence->setMenuBar(menubar);
        statusbar = new QStatusBar(Confluence);
        statusbar->setObjectName("statusbar");
        Confluence->setStatusBar(statusbar);

        retranslateUi(Confluence);

        QMetaObject::connectSlotsByName(Confluence);
    } // setupUi

    void retranslateUi(QMainWindow *Confluence)
    {
        Confluence->setWindowTitle(QCoreApplication::translate("Confluence", "MainWindow", nullptr));
        pushButton->setText(QCoreApplication::translate("Confluence", "Back", nullptr));
        label->setText(QCoreApplication::translate("Confluence", "   Child 1 Page Title", nullptr));
        label_2->setText(QCoreApplication::translate("Confluence", "Scrummy", nullptr));
        label_3->setText(QCoreApplication::translate("Confluence", "Logo", nullptr));
        pushButton_2->setText(QCoreApplication::translate("Confluence", "Delete", nullptr));
        label_4->setText(QCoreApplication::translate("Confluence", "Owned by User 1", nullptr));
        label_5->setText(QCoreApplication::translate("Confluence", "Attachments", nullptr));
        label_6->setText(QCoreApplication::translate("Confluence", "Comments", nullptr));
        label_7->setText(QCoreApplication::translate("Confluence", "Add a comment", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Confluence: public Ui_Confluence {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CONFLUENCE_H
