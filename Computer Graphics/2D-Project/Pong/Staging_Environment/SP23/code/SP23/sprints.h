#ifndef SPRINTS_H
#define SPRINTS_H

#include <QMainWindow>

namespace Ui {
class Sprints;
}

class Sprints : public QMainWindow
{
    Q_OBJECT

public:
    explicit Sprints(QWidget *parent = nullptr);
    ~Sprints();

private:
    Ui::Sprints *ui;
};

#endif // SPRINTS_H
