/*
 * Copyright 2017 github.com/kaaz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package discordbot.db.model;

import discordbot.db.AbstractModel;

/**
 * Created on 10-8-2016
 */
public class OGuild extends AbstractModel {
    public int id = 0;
    public long discord_id = 0L;
    public String name = "";
    public int owner = 0;
    public int active = 0;
    public int banned = 0;

    public boolean isBanned() {
        return banned == 1;
    }
}
