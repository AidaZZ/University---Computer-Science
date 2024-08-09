#ifndef TASKBOARD_H
#define TASKBOARD_H

#include <QMainWindow>

namespace Ui {
class TaskBoard;
}

class TaskBoard : public QMainWindow
{
    Q_OBJECT

public:
    explicit TaskBoard(QWidget *parent = nullptr);
    ~TaskBoard();

private:
    Ui::TaskBoard *ui;
};

#endif // TASKBOARD_H
