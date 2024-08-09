#include "confluence.h"
#include "ui_confluence.h"

Confluence::Confluence(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Confluence)
{
    ui->setupUi(this);
}

Confluence::~Confluence()
{
    delete ui;
}
