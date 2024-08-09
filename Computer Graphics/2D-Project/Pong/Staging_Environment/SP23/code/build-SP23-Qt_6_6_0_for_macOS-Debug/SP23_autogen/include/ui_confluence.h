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
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Confluence
{
public:
    QMenuBar *menubar;
    QWidget *centralwidget;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Confluence)
    {
        if (Confluence->objectName().isEmpty())
            Confluence->setObjectName("Confluence");
        Confluence->resize(800, 600);
        menubar = new QMenuBar(Confluence);
        menubar->setObjectName("menubar");
        Confluence->setMenuBar(menubar);
        centralwidget = new QWidget(Confluence);
        centralwidget->setObjectName("centralwidget");
        Confluence->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(Confluence);
        statusbar->setObjectName("statusbar");
        Confluence->setStatusBar(statusbar);

        retranslateUi(Confluence);

        QMetaObject::connectSlotsByName(Confluence);
    } // setupUi

    void retranslateUi(QMainWindow *Confluence)
    {
        Confluence->setWindowTitle(QCoreApplication::translate("Confluence", "MainWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Confluence: public Ui_Confluence {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CONFLUENCE_H
