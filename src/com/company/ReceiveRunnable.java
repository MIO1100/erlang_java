package com.company;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;

import javax.swing.*;

public class ReceiveRunnable implements Runnable {

    private OtpMbox jProcess;
    private JLabel ResponseLabel;

    public ReceiveRunnable(OtpMbox jProcess, JLabel responseLabel) {
        this.jProcess = jProcess;
        ResponseLabel = responseLabel;
    }

    @Override
    public void run() {
        try {
            OtpErlangObject response = jProcess.receive(1000);
            if (response != null) {
                ResponseLabel.setText(((OtpErlangString) ((OtpErlangTuple) response).elementAt(1))
                        .stringValue());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
