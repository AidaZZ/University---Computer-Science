#include "backlog.h"
#include "ui_backlog.h"

Backlog::Backlog(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Backlog)
{
    ui->setupUi(this);
}

Backlog::~Backlog()
{
    delete ui;
}
