package com.feup.mbc.modbus;

import java.net.InetAddress;
import java.net.UnknownHostException;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.SimpleRegister;

public class ModBusInterface {
    	private TCPMasterConnection con;

    	public ModBusInterface(int port) throws UnknownHostException {
    		InetAddress addr = InetAddress.getByName("localhost");
    		  
    		con = new TCPMasterConnection(addr);
    		con.setPort(port);
    		try {
				con.connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}

		public boolean getDigitalOut(int out) {
			ReadCoilsRequest req = new ReadCoilsRequest(out, 1);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
	    	trans.setRequest(req);

	    	try {
				trans.execute();
			} catch (ModbusIOException e) {
				e.printStackTrace();
			} catch (ModbusSlaveException e) {
				e.printStackTrace();
			} catch (ModbusException e) {
				e.printStackTrace();
			}
			ReadCoilsResponse res = (ReadCoilsResponse) trans.getResponse();
	    	return res.getCoilStatus(0);
		}

		public void setDigitalOut(int out, boolean b) {
			WriteCoilRequest req = new WriteCoilRequest(out, b);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
	    	trans.setRequest(req);

	    	try {
				trans.execute();
			} catch (ModbusIOException e) {
				e.printStackTrace();
			} catch (ModbusSlaveException e) {
				e.printStackTrace();
			} catch (ModbusException e) {
				e.printStackTrace();
			}
		}

		public boolean getDigitalIn(int in) {
			ReadInputDiscretesRequest req = new ReadInputDiscretesRequest(in, 1);

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
	    	trans.setRequest(req);

	    	try {
				trans.execute();
			} catch (ModbusIOException e) {
				e.printStackTrace();
			} catch (ModbusSlaveException e) {
				e.printStackTrace();
			} catch (ModbusException e) {
				e.printStackTrace();
			}
			try {
				ReadInputDiscretesResponse res = (ReadInputDiscretesResponse) trans.getResponse();
		    	return res.getDiscretes().getBit(0);
			} catch (ClassCastException e) {
				trans.getResponse();
				return false;
			}
		}

		public int getRegister(int reg) {
			ReadInputRegistersRequest req = new ReadInputRegistersRequest(reg, 1);
			
			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
	    	trans.setRequest(req);

	    	try {
				trans.execute();
			} catch (ModbusIOException e) {
				e.printStackTrace();
			} catch (ModbusSlaveException e) {
				e.printStackTrace();
			} catch (ModbusException e) {
				e.printStackTrace();
			}
			try {
				ReadInputRegistersResponse res = (ReadInputRegistersResponse) trans.getResponse();
		    	return res.getRegisterValue(0);
			} catch (ClassCastException e) {
				trans.getResponse();
				return -1;
			}
		}

		public void setRegister(int offset, int value) {
			WriteSingleRegisterRequest req = new WriteSingleRegisterRequest(offset, new SimpleRegister(value));

			ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
	    	trans.setRequest(req);

	    	try {
				trans.execute();
			} catch (ModbusIOException e) {
				e.printStackTrace();
			} catch (ModbusSlaveException e) {
				e.printStackTrace();
			} catch (ModbusException e) {
				e.printStackTrace();
			}
		}

}
