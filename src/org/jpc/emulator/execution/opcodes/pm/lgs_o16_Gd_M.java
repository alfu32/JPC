package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class lgs_o16_Gd_M extends Executable
{
    final int op1Index;
    final Address op2;

    public lgs_o16_Gd_M(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        op2 = new Address();//won't work any more delete soon
    }


    public lgs_o16_Gd_M(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1Index = FastDecoder.Gd(modrm);
        op2 = Modrm.getPointer(prefices, modrm, input);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        int addr = op2.get(cpu) + op2.getBase(cpu);
        cpu.gs(0xFFFF & cpu.linearMemory.getWord(addr+2));
        op1.set32(cpu.linearMemory.getWord(addr));
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