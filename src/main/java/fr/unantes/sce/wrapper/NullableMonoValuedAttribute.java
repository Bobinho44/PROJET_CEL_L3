package fr.unantes.sce.wrapper;

/**
 * NullableMonoValuedAttribute is a monovalued attribute wrapper that can be empty
 */
public class NullableMonoValuedAttribute<T> extends MonoValuedAttribute<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void unset() {
        super.unset();
    }

}