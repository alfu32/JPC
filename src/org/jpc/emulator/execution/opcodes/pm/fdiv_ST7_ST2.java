package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class fdiv_ST7_ST2 extends Executable
{

    public fdiv_ST7_ST2(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }


    public fdiv_ST7_ST2(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
    }

    public Branch execute(Processor cpu)
    {
        double freg0 = cpu.fpu.ST(7);
        double freg1 = cpu.fpu.ST(2);
        if (((freg0 == 0.0) && (freg1 == 0.0)) || (Double.isInfinite(freg0) && Double.isInfinite(freg1)))
            cpu.fpu.setInvalidOperation();
	if ((freg0 == 0.0) && !Double.isNaN(freg1) && !Double.isInfinite(freg1))
            cpu.fpu.setZeroDivide();
        cpu.fpu.setST(7,  freg0/freg1);
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}