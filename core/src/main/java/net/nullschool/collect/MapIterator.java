/*
 * Copyright 2013 Cameron Beccario
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

package net.nullschool.collect;

import java.util.*;


/**
 * 2013-02-12<p/>
 *
 * An iterator that iterates over the entries of a map while providing direct access to each entry's key and value.
 * This iterator is suitable for maps that do not use {@link Map.Entry} objects as part of their internal
 * representation. By providing direct access to the keys and values, developers can use this iterator to avoid
 * unnecessary creation of {@link Map.Entry} objects that would otherwise conventionally occur when iterating over
 * those maps' {@link Map#entrySet entrySets}.<p/>
 *
 * Native support for this iterator is provided by maps that implement {@link IterableMap}. For convenience,
 * MapIterator instances can be adapted on top of standard maps using {@link IteratorTools#newMapIterator}. Although
 * they provide no performance benefit, these MapIterator adapters can simplify code that needs to operate commonly
 * on both standard maps and IterableMap instances.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author Cameron Beccario
 */
public interface MapIterator<K, V> extends Iterator<K> {

    /**
     * Returns {@code true} if the iteration has more entries. When {@code true} is returned, then the subsequent
     * call to {@link #next} will not throw {@link NoSuchElementException}.
     *
     * @return {@code true} if the iteration has more entries.
     */
    @Override boolean hasNext();

    /**
     * Moves the iteration to the next entry and returns its key.
     *
     * @return the next entry's key
     * @throws NoSuchElementException if the iteration has no more entries.
     */
    @Override K next();

    /**
     * Returns the value of the entry last successfully moved to in this iteration. In other words, this method
     * returns the value associated with the key returned by the last successful invocation of {@link #next}.
     *
     * @return the value of this iteration's current entry.
     * @throws IllegalStateException if there is no current entry because {@link #next} has not been called or
     *                               the entry has been successfully removed (see {@link #remove}).
     */
    V value();

    /**
     * Returns the entry last successfully moved to in this iteration. In other words, this method returns the
     * entry associated with the key returned by the last successful invocation of {@link #next}.<p/>
     *
     * Implementer's note: the classes {@link AbstractEntry}, {@link AbstractMap.SimpleEntry}, and {@link
     * AbstractMap.SimpleImmutableEntry} provide implementations that satisfy the contract for {@link Map.Entry}.
     *
     * @return this iteration's current entry.
     * @throws IllegalStateException if there is no current entry because {@link #next} has not been called or
     *                               the entry has been successfully removed (see {@link #remove}).
     */
    Map.Entry<K, V> entry();

    /**
     * Removes the current entry from the map (optional operation). In other words, this method removes the
     * entry associated with the key returned by the last successful invocation of {@link #next}. The behavior
     * of this iterator is undefined if the underlying map is modified during iteration by any means other than
     * invocation of this method.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override void remove();
}
