/********************************************************************************
** Form generated from reading UI file 'backlog.ui'
**
** Created by: Qt User Interface Compiler version 6.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_BACKLOG_H
#define UI_BACKLOG_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Backlog
{
public:
    QMenuBar *menubar;
    QWidget *centralwidget;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Backlog)
    {
        if (Backlog->objectName().isEmpty())
            Backlog->setObjectName("Backlog");
        Backlog->resize(800, 600);
        menubar = new QMenuBar(Backlog);
        menubar->setObjectName("menubar");
        Backlog->setMenuBar(menubar);
        centralwidget = new QWidget(Backlog);
        centralwidget->setObjectName("centralwidget");
        Backlog->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(Backlog);
        statusbar->setObjectName("statusbar");
        Backlog->setStatusBar(statusbar);

        retranslateUi(Backlog);

        QMetaObject::connectSlotsByName(Backlog);
    } // setupUi

    void retranslateUi(QMainWindow *Backlog)
    {
        Backlog->setWindowTitle(QCoreApplication::translate("Backlog", "MainWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Backlog: public Ui_Backlog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_BACKLOG_H
