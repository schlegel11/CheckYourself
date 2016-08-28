package ui;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * {@link EntityWrapper} for decoupling the specific entity classes.
 *
 * @param <TEntityType> The specific entity class type.
 */
public class EntityWrapper<TEntityType> {

    private final TEntityType entity;

    private EntityWrapper(TEntityType entity) {
        this.entity = entity;
    }

    public static <TEntityType> EntityWrapper<TEntityType> newEntityWrapper(TEntityType entity) {
        return new EntityWrapper<>(entity);
    }

    public static <TEntityType> Collection<EntityWrapper<TEntityType>> entitiesToNewEntityWrapperCollection(
            Collection<TEntityType> entities) {
        return entities.stream().map(EntityWrapper::new).collect(Collectors.toList());
    }

    public TEntityType getEntity() {
        return entity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntityWrapper other = (EntityWrapper) obj;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
            return false;
        return true;
    }
}
