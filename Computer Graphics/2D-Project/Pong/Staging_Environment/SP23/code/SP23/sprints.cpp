#include "sprints.h"
#include "ui_sprints.h"

Sprints::Sprints(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Sprints)
{
    ui->setupUi(this);
}

Sprints::~Sprints()
{
    delete ui;
}
