package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class sbb_Gb_Eb extends Executable
{
    final int op1Index;
    final int op2Index;

    public sbb_Gb_Eb(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        op2Index = Processor.getRegIndex(parent.operand[1].toString());
    }


    public sbb_Gb_Eb(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1Index = FastDecoder.Gb(modrm);
        op2Index = FastDecoder.Eb(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        Reg op2 = cpu.regs[op2Index];
        int add = (cpu.cf()? 1: 0);
        cpu.flagOp1 = op1.get8();
        cpu.flagOp2 = op2.get8();
        cpu.flagResult = (byte)(cpu.flagOp1 - (cpu.flagOp2 + add));
        op1.set8((byte)cpu.flagResult);
        cpu.flagIns = UCodes.SBB8;
        cpu.flagStatus = OSZAPC;
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