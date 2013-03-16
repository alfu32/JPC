package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class add_Gb_Eb_mem extends Executable
{
    final int op1Index;
    final Pointer op2;

    public add_Gb_Eb_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        op2 = new Pointer(parent.operand[1], parent.adr_mode);
    }


    public add_Gb_Eb_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1Index = FastDecoder.Gb(modrm);
        op2 = Modrm.getPointer(prefices, modrm, input);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        cpu.flagOp1 = op1.get8();
        cpu.flagOp2 = op2.get8(cpu);
        cpu.flagResult = (byte)(cpu.flagOp1 + cpu.flagOp2);
        op1.set8((byte)cpu.flagResult);
        cpu.flagIns = UCodes.ADD8;
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