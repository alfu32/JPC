package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class fcomp_ST0_ST5 extends Executable
{

    public fcomp_ST0_ST5(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
    }


    public fcomp_ST0_ST5(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
    }

    public Branch execute(Processor cpu)
    {
                            int newcode = 0xd;
        double freg0 = cpu.fpu.ST(0);
        double freg1 = cpu.fpu.ST(5);
        if (Double.isNaN(freg0) || Double.isNaN(freg1))
            cpu.fpu.setInvalidOperation();
        else {
            if (freg0 > freg1) newcode = 0;
            else if (freg0 < freg1) newcode = 1;
            else newcode = 8;
        }
        cpu.fpu.conditionCode &= 2;
        cpu.fpu.conditionCode |= newcode;
        cpu.fpu.pop();
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