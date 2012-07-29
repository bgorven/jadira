/*
 *  Copyright 2010, 2011 Christopher Pheby
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.jadira.usertype.dateandtime.joda;

import org.jadira.usertype.dateandtime.joda.testmodel.YearsHolder;
import org.jadira.usertype.dateandtime.shared.dbunit.AbstractDatabaseTest;
import org.joda.time.Years;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPersistentYears extends AbstractDatabaseTest<YearsHolder> {

    private static final Years[] years = new Years[]{Years.years(1), Years.years(2010), Years.years(1999)};

    public TestPersistentYears() {
        super(YearsHolder.class);
    }

    @Test
    public void testPersist() {

        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        for (int i = 0; i < years.length; i++) {

            YearsHolder item = new YearsHolder();
            item.setId(i);
            item.setName("test_" + i);
            item.setYear(years[i]);

            manager.persist(item);
        }

        manager.flush();

        manager.getTransaction().commit();

        manager.close();

        manager = factory.createEntityManager();

        for (int i = 0; i < years.length; i++) {

            YearsHolder item = manager.find(YearsHolder.class, Long.valueOf(i));

            assertNotNull(item);
            assertEquals(i, item.getId());
            assertEquals("test_" + i, item.getName());
            assertEquals(years[i], item.getYear());
        }

        verifyDatabaseTable(manager, YearsHolder.class.getAnnotation(Table.class).name());

        manager.close();
    }
}
