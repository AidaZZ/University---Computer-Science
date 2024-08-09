#ifndef REGISTRATION_WINDOW_H
#define REGISTRATION_WINDOW_H

#include <QMainWindow>

namespace Ui {
class registration_window;
}

class registration_window : public QMainWindow
{
    Q_OBJECT

public:
    explicit registration_window(QWidget *parent = nullptr);
    ~registration_window();

private:
    Ui::registration_window *ui;
};

#endif // REGISTRATION_WINDOW_H
