package com.alibaba.bytekit.asm.binding;

import com.alibaba.deps.org.objectweb.asm.Type;
import com.alibaba.deps.org.objectweb.asm.tree.InsnList;
import com.alibaba.deps.org.objectweb.asm.tree.MethodInsnNode;
import com.alibaba.bytekit.asm.location.Location;
import com.alibaba.bytekit.asm.location.MethodInsnNodeWare;
import com.alibaba.bytekit.utils.AsmOpUtils;

/**
 * 
 * @author hengyunabc 2020-05-02
 *
 */
public class InvokeMethodOwnerBinding extends Binding {

    @Override
    public void pushOntoStack(InsnList instructions, BindingContext bindingContext) {
        Location location = bindingContext.getLocation();
        if (location instanceof MethodInsnNodeWare) {
            MethodInsnNodeWare methodInsnNodeWare = (MethodInsnNodeWare) location;
            MethodInsnNode methodInsnNode = methodInsnNodeWare.methodInsnNode();
            AsmOpUtils.push(instructions, methodInsnNode.owner);

        } else {
            throw new IllegalArgumentException(
                    "InvokeMethodOwnerBinding location is not Invocation location, location: " + location);
        }

    }

    @Override
    public Type getType(BindingContext bindingContext) {
        return Type.getType(String.class);
    }

}
