package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class shld_Ew_Gw_CL_mem extends Executable
{
    final Pointer op1;
    final int op2Index;

    public shld_Ew_Gw_CL_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1 = new Pointer(parent.operand[0], parent.adr_mode);
        op2Index = Processor.getRegIndex(parent.operand[1].toString());
    }


    public shld_Ew_Gw_CL_mem(int blockStart, int eip, int prefices, PeekableInputStream input)
    {
        super(blockStart, eip);
        op1 = Modrm.getPointer(prefices, modrm, input);
        op2Index = FastDecoder.Gw(modrm);
    }

    public Branch execute(Processor cpu)
    {
        Reg op2 = cpu.regs[op2Index];
        if(cpu.r_cl.get8() != 0)
        {
            int shift = cpu.r_cl.get8() & 0x1f;
            if (shift <= 16)
                cpu.flagOp1 = op1.get16(cpu);
            else
                cpu.flagOp1 = op2.get16();
            cpu.flagOp2 = shift;
            long rot = ((long)(0xFFFF&op1.get16(cpu)) << (2*16)) | ((0xffffffffL & 0xFFFF&op2.get16()) << 16) | (0xFFFF&op1.get16(cpu));
            cpu.flagResult = (short)((int)((rot << shift) | (rot >>> (2*16-shift))));
            op1.set16(cpu, (short)cpu.flagResult);
            cpu.flagIns = UCodes.SHLD16;
            cpu.flagStatus = OSZAPC;
        }
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