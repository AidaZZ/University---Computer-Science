#ifndef CONFLUENCE_H
#define CONFLUENCE_H

#include <QMainWindow>

namespace Ui {
class Confluence;
}

class Confluence : public QMainWindow
{
    Q_OBJECT

public:
    explicit Confluence(QWidget *parent = nullptr);
    ~Confluence();

private:
    Ui::Confluence *ui;
};

#endif // CONFLUENCE_H
