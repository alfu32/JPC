package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class call_o16_Ep_mem extends Executable
{
        final Pointer offset;
    final int blockLength;
    final int instructionLength;

    public call_o16_Ep_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        blockLength = parent.x86Length+(int)parent.eip-blockStart;
        instructionLength = parent.x86Length;
        offset = new Pointer(parent.operand[0], parent.adr_mode);
    }


    public call_o16_Ep_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        offset = Modrm.getPointer(modrm);
        instructionLength = (int)input.getAddress()-eip;
        blockLength = (int)input.getAddress()-blockStart;
    }

    public Branch execute(Processor cpu)
    {
        int cs = offset.get16(cpu, 2);
        int targetEip = offset.get16(cpu);
        cpu.eip += blockLength;
        cpu.callFar(cs, (short)targetEip);
        return Branch.Call_Unknown;
    }

    public boolean isBranch()
    {
        return true;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}