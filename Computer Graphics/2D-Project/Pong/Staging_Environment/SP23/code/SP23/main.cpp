#include "mainwindow.h"
//#include "registration_window.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    //Creating an object of MainWindow to access its attributes
    MainWindow w;
    //registration_window rw;
    //rw.showMaximized();
    w.showMaximized();
    return app.exec();
}
// This is a test to see pushing works from git desktop
