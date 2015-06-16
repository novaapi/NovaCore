package nova.core.util;import com.google.common.collect.ImmutableList;import nova.internal.core.Game;import se.jbee.inject.Dependency;import java.util.Arrays;import java.util.Optional;import java.util.function.Consumer;public final class Factory<T extends Buildable> implements Identifiable {	public final Class<? extends T> clazz;	private final ImmutableList<Consumer<T>> finalizers;	public final Optional<String> ID;	private final Optional<Object[]> typeArguments;	private Factory(Class<? extends T> clazz) {		this.clazz = clazz;		this.finalizers = ImmutableList.<Consumer<T>>builder().build();		this.ID = Optional.empty();		this.typeArguments = Optional.empty();	}	private Factory(Class<? extends T> clazz, ImmutableList<Consumer<T>> finalizers, Optional<String> ID, Optional<Object[]> typeArguments) {		this.clazz = clazz;		this.finalizers = finalizers;		this.ID = ID;		this.typeArguments = typeArguments;	}	private Factory(Factory<T> factory) {		this(factory.clazz, factory.finalizers, factory.ID, factory.typeArguments);	}	public static <T extends Buildable> Factory<T> of(Class<T> clazz) {		return new Factory<>(clazz);	}	public static <T extends Buildable<S>, S extends Buildable<S>> Factory<S> of(T obj) {		return obj.factory();	}	public Factory<T> typeArguments(Object... args) {		return new Factory<>(this.clazz, this.finalizers, this.ID, Optional.of(Arrays.copyOf(args, args.length)));	}	public Factory<T> finalizer(Consumer<T> finalizer) {		return new Factory<>(this.clazz, ImmutableList.<Consumer<T>>builder().addAll(this.finalizers).add(finalizer).build(), ID, typeArguments);	}	public Factory<T> ID(String ID) {		return new Factory<>(this.clazz, this.finalizers, Optional.of(ID), typeArguments);	}	public T make(Object... args) {		T obj = Game.resolve(Dependency.dependency(clazz).named(getID()));		ReflectionUtil.injectField("ID", obj, getID());		obj.afterConstruction(typeArguments, Optional.ofNullable(args));		finalizers.stream().forEach((cons) -> cons.accept(obj));		obj.afterFinalizers();		return obj;	}	@SuppressWarnings("NullArgumentToVariableArgMethod")	public T make() {		return make(null);	}	@Override	public Factory<T> clone() {		return new Factory<>(this);	}	@Override	public String getID() {		return ID.orElseGet(clazz::getCanonicalName);	}	@Override	public boolean equals(Object obj) {		return obj == this || obj instanceof Factory && sameType((Factory) obj);	}	@Override	public String toString() {		return String.format("ID: %s, class: %s, type arguments: [%s]", getID(), clazz, typeArguments.map(Arrays::toString).orElseGet(() -> "none"));	}	@Override	public int hashCode() {		return getID().hashCode();	}}