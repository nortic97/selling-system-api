package com.nortic.posdash.models.enums;

public enum FileTypeEnum {
    PROFILE_PIC_FOLDER("profile_pic"),
    INVOICES_FOLDER("invoices"),
    OTHER("other");

    private final String directoryName;

    FileTypeEnum(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public static FileTypeEnum fromString(String fileType) {
        for (FileTypeEnum type : FileTypeEnum.values()) {
            if (type.name().equalsIgnoreCase(fileType)) {
                return type;
            }
        }
        return FileTypeEnum.OTHER;
    }

}
