/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2021 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.retrooper.packetevents.protocol.data.chat;

public class ClickEvent {
    private ClickType type;
    private String value;

    public ClickEvent(ClickType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ClickEvent(ClickType type) {
        this(type, "");
    }

    public ClickType getType() {
        return type;
    }

    public void setType(ClickType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClickEvent) {
            ClickEvent clickEvent = (ClickEvent) obj;
            if (clickEvent.type == type && clickEvent.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public enum ClickType {
        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        SUGGEST_COMMAND,
        CHANGE_PAGE,
        COPY_TO_CLIPBOARD;

        private final String name;

        ClickType() {
            this.name = name().toLowerCase();
        }

        public String getName() {
            return name;
        }
    }
}
