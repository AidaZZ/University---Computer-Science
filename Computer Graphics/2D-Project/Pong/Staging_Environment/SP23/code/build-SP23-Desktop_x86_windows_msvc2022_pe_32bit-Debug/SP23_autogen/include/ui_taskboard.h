/********************************************************************************
** Form generated from reading UI file 'taskboard.ui'
**
** Created by: Qt User Interface Compiler version 6.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TASKBOARD_H
#define UI_TASKBOARD_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_TaskBoard
{
public:
    QMenuBar *menubar;
    QWidget *centralwidget;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *TaskBoard)
    {
        if (TaskBoard->objectName().isEmpty())
            TaskBoard->setObjectName("TaskBoard");
        TaskBoard->resize(800, 600);
        menubar = new QMenuBar(TaskBoard);
        menubar->setObjectName("menubar");
        TaskBoard->setMenuBar(menubar);
        centralwidget = new QWidget(TaskBoard);
        centralwidget->setObjectName("centralwidget");
        TaskBoard->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(TaskBoard);
        statusbar->setObjectName("statusbar");
        TaskBoard->setStatusBar(statusbar);

        retranslateUi(TaskBoard);

        QMetaObject::connectSlotsByName(TaskBoard);
    } // setupUi

    void retranslateUi(QMainWindow *TaskBoard)
    {
        TaskBoard->setWindowTitle(QCoreApplication::translate("TaskBoard", "MainWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class TaskBoard: public Ui_TaskBoard {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TASKBOARD_H
