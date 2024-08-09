#include "registration_window.h"
#include "ui_registration_window.h"

registration_window::registration_window(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::registration_window)
{
    ui->setupUi(this);
}

registration_window::~registration_window()
{
    delete ui;
}
