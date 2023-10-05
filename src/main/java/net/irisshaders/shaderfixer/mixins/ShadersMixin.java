package net.irisshaders.shaderfixer.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadersmod.client.Shaders;

@Mixin(value = Shaders.class, remap = false)
public abstract class ShadersMixin {
    @Shadow
    private static boolean waterShadowEnabled;

    @Shadow
    public static void setProgramUniform1i(String name, int x) {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static int getTextureIndex(int stage, String name) {
        if (stage == 0) {
            label225: {
                if (name.equals("texture")) {
                    return 0;
                }

                if (name.equals("lightmap")) {
                    return 1;
                }

                if (name.equals("normals")) {
                    return 2;
                }

                if (name.equals("specular")) {
                    return 3;
                }

                if (!name.equals("shadowtex0") && !name.equals("watershadow")) {
                    if (name.equals("shadow")) {
                        return waterShadowEnabled ? 5 : 4;
                    }

                    if (name.equals("shadowtex1")) {
                        return 5;
                    }

                    if (name.equals("depthtex0") || name.equals("gdepthtex")) {
                        return 6;
                    }

                    if (name.equals("colortex4") || name.equals("gaux1")) {
                        return 7;
                    }

                    if (name.equals("colortex5") || name.equals("gaux2")) {
                        return 8;
                    }

                    if (name.equals("colortex6") || name.equals("gaux3")) {
                        return 9;
                    }

                    if (name.equals("colortex7") || name.equals("gaux4")) {
                        return 10;
                    }

                    if (name.equals("depthtex1")) {
                        return 12;
                    }

                    if (!name.equals("shadowcolor0") && !name.equals("shadowcolor")) {
                        if (name.equals("shadowcolor1")) {
                            return 14;
                        }

                        if (name.equals("noisetex")) {
                            return 15;
                        }
                        break label225;
                    }

                    return 13;
                }

                return 4;
            }
        }

        if (stage == 1 || stage == 2) {
            if (name.equals("colortex0") || name.equals("colortex0")) {
                return 0;
            }

            if (name.equals("colortex1") || name.equals("gdepth")) {
                return 1;
            }

            if (name.equals("colortex2") || name.equals("gnormal")) {
                return 2;
            }

            if (name.equals("colortex3") || name.equals("composite")) {
                return 3;
            }

            if (name.equals("shadowtex0") || name.equals("watershadow")) {
                return 4;
            }

            if (name.equals("shadow")) {
                return waterShadowEnabled ? 5 : 4;
            }

            if (name.equals("shadowtex1")) {
                return 5;
            }

            if (name.equals("depthtex0") || name.equals("gdepthtex")) {
                return 6;
            }

            if (name.equals("colortex4") || name.equals("gaux1")) {
                return 7;
            }

            if (name.equals("colortex5") || name.equals("gaux2")) {
                return 8;
            }

            if (name.equals("colortex6") || name.equals("gaux3")) {
                return 9;
            }

            if (name.equals("colortex7") || name.equals("gaux4")) {
                return 10;
            }

            if (name.equals("depthtex1")) {
                return 11;
            }

            if (name.equals("depthtex2")) {
                return 12;
            }

            if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
                return 13;
            }

            if (name.equals("shadowcolor1")) {
                return 14;
            }

            if (name.equals("noisetex")) {
                return 15;
            }
        }

        return -1;
    }

    @Inject(method = "setProgramUniform1i", at = @At("TAIL"))
    private static void addColorTexs(String name, int x, CallbackInfo ci) {
        if (name.equals("gaux1")) {
            setProgramUniform1i("colortex4", x);
        } else if (name.equals("gaux2")) {
            setProgramUniform1i("colortex5", x);
        } else if (name.equals("gaux3")) {
            setProgramUniform1i("colortex6", x);
        } else if (name.equals("gaux4")) {
            setProgramUniform1i("colortex7", x);
        }
    }
}
