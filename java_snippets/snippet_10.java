JPanel plContent = new JSubPanel();
plContent.addKeyListener(new KeyAdapter() { //receive messge from JPanel
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getSource() == plContent) {//update main windows
                    updateWindowTips(); //for example else.
                }
            }
        });
