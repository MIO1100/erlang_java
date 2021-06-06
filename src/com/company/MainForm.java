package com.company;

import com.ericsson.otp.erlang.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainForm extends JFrame {
    private JTextArea textArea1;
    private JButton sendButton;
    private JLabel ResponseLabel;
    private JPanel panel;
    private OtpNode send;
    private OtpMbox receive;
    private OtpMbox jProcess;
    private OtpErlangPid jPid;
    private ScheduledExecutorService executor;

    public MainForm(OtpNode javaNode, OtpNode receiveNode) {
        executor = Executors.newSingleThreadScheduledExecutor();
        send = javaNode;
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 600));
        setVisible(true);
        jProcess = javaNode.createMbox();
        receive = receiveNode.createMbox();

        jPid = jProcess.self();
        executor.scheduleAtFixedRate(new ReceiveRunnable(receive, ResponseLabel), 0, 1000, TimeUnit.MILLISECONDS);
        sendButton.addActionListener(actionEvent -> {
            OtpErlangObject[] get_msg = {jPid, new OtpErlangString(textArea1.getText())};
            jProcess.send(receive.self(),  new OtpErlangTuple(get_msg));
        });
    }
}
