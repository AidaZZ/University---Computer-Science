#include "taskboard.h"
#include "ui_taskboard.h"

TaskBoard::TaskBoard(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::TaskBoard)
{
    ui->setupUi(this);
}

TaskBoard::~TaskBoard()
{
    delete ui;
}
