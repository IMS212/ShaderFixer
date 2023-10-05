package net.irisshaders.shaderfixer.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadersmod.client.ShaderParser;

import java.util.regex.Pattern;

@Mixin(ShaderParser.class)
public class ShaderParserMixin {
    @Shadow
    public static Pattern PATTERN_ATTRIBUTE;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void replacePattern(CallbackInfo ci) {
        PATTERN_ATTRIBUTE = Pattern.compile("\\s*(attribute|in)\\s+\\w+\\s+(\\w+).*");

    }
}
