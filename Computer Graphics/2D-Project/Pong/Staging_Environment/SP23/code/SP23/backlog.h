#ifndef BACKLOG_H
#define BACKLOG_H

#include <QMainWindow>

namespace Ui {
class Backlog;
}

class Backlog : public QMainWindow
{
    Q_OBJECT

public:
    explicit Backlog(QWidget *parent = nullptr);
    ~Backlog();

private:
    Ui::Backlog *ui;
};

#endif // BACKLOG_H
