package com.company;

import com.ericsson.otp.erlang.*;


public class Main {


    public static void main(String[] args) throws Exception {
        System.setProperty("OtpConnection.trace", "0");
        OtpNode javaNode = new OtpNode("pidWarehouse@192.168.1.6");
        OtpNode receiveNode = new OtpNode("pidDelivery@192.168.1.6" );
        if (javaNode.ping("pidWarehouse@192.168.1.6", 10000) && javaNode.ping("pidDelivery@192.168.1.6", 10000)
                && receiveNode.ping("pidWarehouse@192.168.1.6", 10000) && receiveNode.ping("pidDelivery@192.168.1.6", 10000)) {
            new MainForm(javaNode, receiveNode);
        }

    }
}
