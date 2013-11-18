package com.bastard.cls.info.attribute.stackmap;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.Info;
import com.bastard.util.Indent;

public class StackMapFrameInfo implements Info {
	
	private StackMapFrameImpl frame;

	@Override
	public StackMapFrameInfo read(ConstantPool pool, ByteBuffer data) {
		int tag = data.get();
		if (tag >= 0 && tag <= 63) {
			//frame = new SameFrameImpl(tag).read(pool, data);
			System.out.println("frame: 1");
		} else if (tag >= 64 && tag <= 127) {
			//frame = new SameLocals1StackItemFrameImpl(tag).read(pool, data);
			System.out.println("frame: 2");
			data.get();
		} else if (tag == 247) {
			//frame = new SameLocals1StackItemFrameExtendedImpl(tag).read(pool, data);
			System.out.println("frame: 3");
			data.getShort();
			data.get();
		} else if (tag >= 248 && tag <= 250) {
			//frame = new ChopFrameImpl(tag).read(pool, data);
			System.out.println("frame: 4");
			data.getShort();
		} else if (tag == 251) {
			//frame = new SameFrameExtendedImpl(tag).read(pool, data);
			System.out.println("frame: 5");
			data.getShort();
		} else if (tag >= 252 && tag <= 254) {
			//frame = new AppendFrameImpl(tag).read(pool, data);
			System.out.println("frame: 6");
			data.getShort();
			for (int i = 0; i < tag - 251; i++) {
				data.get();
			}
		} else if (tag == 255) {
			//frame = new FullFrameImpl(tag).read(pool, data);
			System.out.println("frame: 7");
			data.getShort();
			for (int i = 0; i < data.getShort(); i++) {
				data.get();
			}
			for (int i = 0; i < data.getShort(); i++) {
				data.get();
			}
		} else {
			System.err.println("Unknown stack map frame tag: " + tag);
		}
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}
	
	@Override
	public String toString() {
		return "StackMapFrameInfo[" + frame + "]";
	}
	
	public StackMapFrameImpl getFrame() {
		return frame;
	}

}
