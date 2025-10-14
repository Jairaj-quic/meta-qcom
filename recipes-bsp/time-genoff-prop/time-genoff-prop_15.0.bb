SUMMARY = "Prebuilt Qualcomm Time Genoff Library"
DESCRIPTION = "Prebuilt Time Genoff Library for time synchronization services"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/usr/share/doc/${PN}/NOLOGINBINARYLICENSEQTI.pdf;md5=4ceffe94cb40cdce6d2f4fb93cc063d1 \
                    file://${UNPACKDIR}/usr/share/doc/${PN}/NOTICE;md5=4b722aa0574e24873e07b94e40b92e4d"

PBT_BUILD_DATE = "250929.2"
ARTIFACTORY_URL = "https://qartifactory-edge.qualcomm.com/artifactory/qsc_releases/software/chip/component/core-technologies.qclinux.0.0/${PBT_BUILD_DATE}/prebuilt_yocto"
PBT_ARCH = "armv8a"

SRC_URI = "${ARTIFACTORY_URL}/time-genoff_15.0_${PBT_ARCH}.tar.gz"
SRC_URI[sha256sum] = "1b9324344045c41137d6b0079470b73328ee2b668518d81e79a2c58826fa68d4"

S = "${UNPACKDIR}"

# This package is currently only used and tested on ARMv8 (aarch64) machines.
# Therefore, builds for other architectures are not necessary and are explicitly excluded.
COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:aarch64 = "(.*)"

do_install() {
    # Install the prebuilt files maintaining the directory structure
    cp -r ${S}/usr ${D}/

    # Create any additional directories that might be needed
    install -d ${D}${libdir}
    install -d ${D}${includedir}
}

INSANE_SKIP:${PN} = "already-stripped"
FILES:${PN} = "${libdir}/lib*.so*"
FILES:${PN} += "/usr/include/*"
ALLOW_EMPTY:${PN} = "1"
