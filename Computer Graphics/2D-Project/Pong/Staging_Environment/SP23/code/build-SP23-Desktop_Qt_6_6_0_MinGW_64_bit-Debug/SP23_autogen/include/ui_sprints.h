/********************************************************************************
** Form generated from reading UI file 'sprints.ui'
**
** Created by: Qt User Interface Compiler version 6.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SPRINTS_H
#define UI_SPRINTS_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Sprints
{
public:
    QMenuBar *menubar;
    QWidget *centralwidget;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Sprints)
    {
        if (Sprints->objectName().isEmpty())
            Sprints->setObjectName("Sprints");
        Sprints->resize(800, 600);
        menubar = new QMenuBar(Sprints);
        menubar->setObjectName("menubar");
        Sprints->setMenuBar(menubar);
        centralwidget = new QWidget(Sprints);
        centralwidget->setObjectName("centralwidget");
        Sprints->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(Sprints);
        statusbar->setObjectName("statusbar");
        Sprints->setStatusBar(statusbar);

        retranslateUi(Sprints);

        QMetaObject::connectSlotsByName(Sprints);
    } // setupUi

    void retranslateUi(QMainWindow *Sprints)
    {
        Sprints->setWindowTitle(QCoreApplication::translate("Sprints", "MainWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Sprints: public Ui_Sprints {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SPRINTS_H
