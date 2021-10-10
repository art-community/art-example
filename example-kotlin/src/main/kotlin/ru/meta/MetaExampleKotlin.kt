package ru.meta

import io.art.meta.model.InstanceMetaMethod
import io.art.meta.model.MetaClass
import io.art.meta.model.MetaConstructor
import io.art.meta.model.MetaField
import io.art.meta.model.MetaLibrary
import io.art.meta.model.MetaMethod
import io.art.meta.model.MetaPackage
import io.art.meta.model.MetaParameter
import io.art.meta.model.MetaProxy
import io.art.meta.model.MetaType.metaArray
import io.art.meta.model.MetaType.metaEnum
import io.art.meta.model.MetaType.metaType
import io.art.meta.model.StaticMetaMethod
import java.util.function.Function
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.Throwable
import kotlin.collections.Map
import kotlin.jvm.Throws
import kotlin.sequences.Sequence
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.communicator.MyCommunicator
import ru.communicator.MyConnector
import ru.model.Model
import ru.service.MyService

@Suppress("warnings")
public class MetaExampleKotlin : MetaLibrary {
  private val ruPackage: MetaRuPackage = register(MetaRuPackage())

  public constructor(vararg dependencies: MetaLibrary) : super(dependencies)

  public fun ruPackage(): MetaRuPackage = ruPackage

  public class MetaRuPackage : MetaPackage {
    private val communicatorPackage: MetaCommunicatorPackage = register(MetaCommunicatorPackage())

    private val servicePackage: MetaServicePackage = register(MetaServicePackage())

    private val modelPackage: MetaModelPackage = register(MetaModelPackage())

    internal constructor() : super("ru")

    public fun communicatorPackage(): MetaCommunicatorPackage = communicatorPackage

    public fun servicePackage(): MetaServicePackage = servicePackage

    public fun modelPackage(): MetaModelPackage = modelPackage

    public class MetaCommunicatorPackage : MetaPackage {
      private val myCommunicatorClass: MetaMyCommunicatorClass = register(MetaMyCommunicatorClass())

      private val myConnectorClass: MetaMyConnectorClass = register(MetaMyConnectorClass())

      internal constructor() : super("communicator")

      public fun myCommunicatorClass(): MetaMyCommunicatorClass = myCommunicatorClass

      public fun myConnectorClass(): MetaMyConnectorClass = myConnectorClass

      public class MetaMyCommunicatorClass : MetaClass<MyCommunicator> {
        private final val myMethodMethod: MetaMyMethodMethod = register(MetaMyMethodMethod())

        private final val getModelMethod: MetaGetModelMethod = register(MetaGetModelMethod())

        private final val compensationMethod: MetaCompensationMethod =
            register(MetaCompensationMethod())

        internal constructor() : super(metaType<MyCommunicator>(MyCommunicator::class.java))

        public fun myMethodMethod(): MetaMyMethodMethod = myMethodMethod

        public fun getModelMethod(): MetaGetModelMethod = getModelMethod

        public fun compensationMethod(): MetaCompensationMethod = compensationMethod

        public override fun proxy(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>): MetaProxy
            = MetaMyCommunicatorProxy(invocations)

        public class MetaMyMethodMethod : InstanceMetaMethod<MyCommunicator, Model> {
          private val modelParameter: MetaParameter<Model> = register(MetaParameter(0,
              "model",metaType<Model>(Model::class.java)))

          internal constructor() : super("myMethod",metaType<Model>(Model::class.java))

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.myMethod(arguments[0] as Model)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.myMethod(argument as Model)
          }

          public fun modelParameter(): MetaParameter<Model> = modelParameter
        }

        public class MetaGetModelMethod : InstanceMetaMethod<MyCommunicator, Model> {
          internal constructor() : super("getModel",metaType<Model>(Model::class.java))

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.getModel()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator): Any? {
            return instance.getModel()
          }
        }

        public class MetaCompensationMethod : InstanceMetaMethod<MyCommunicator, Mono<String>> {
          private val inputParameter: MetaParameter<Flux<String>> = register(MetaParameter(0,
              "input",metaType<Flux<String>>(Flux::class.java,metaType<String>(String::class.java))))

          internal constructor() :
              super("compensation",metaType<Mono<String>>(Mono::class.java,metaType<String>(String::class.java)))

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, arguments: Array<Any>): Any? {
            return instance.compensation(arguments[0] as Flux<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyCommunicator, argument: Any): Any? {
            return instance.compensation(argument as Flux<String>)
          }

          public fun inputParameter(): MetaParameter<Flux<String>> = inputParameter
        }

        public inner class MetaMyCommunicatorProxy : MetaProxy, MyCommunicator {
          private final val myMethodInvocation: Function<Any?, Any?>

          private final val getModelInvocation: Function<Any?, Any?>

          private final val compensationInvocation: Function<Any?, Any?>

          public constructor(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>) :
              super(invocations) {
            myMethodInvocation = invocations[myMethodMethod]!!
            getModelInvocation = invocations[getModelMethod]!!
            compensationInvocation = invocations[compensationMethod]!!
          }

          public override fun myMethod(model: Model): Model = myMethodInvocation.apply(model) as
              Model

          public override fun getModel(): Model = getModelInvocation.apply(null) as Model

          public override fun compensation(input: Flux<String>): Mono<String> =
              compensationInvocation.apply(input) as Mono<String>
        }
      }

      public class MetaMyConnectorClass : MetaClass<MyConnector> {
        private final val myMethod: MetaMyMethod = register(MetaMyMethod())

        internal constructor() : super(metaType<MyConnector>(MyConnector::class.java))

        public fun myMethod(): MetaMyMethod = myMethod

        public override fun proxy(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>): MetaProxy
            = MetaMyConnectorProxy(invocations)

        public class MetaMyMethod : InstanceMetaMethod<MyConnector, MyCommunicator> {
          internal constructor() : super("my",metaType<MyCommunicator>(MyCommunicator::class.java))

          @Throws(Throwable::class)
          public override fun invoke(instance: MyConnector, arguments: Array<Any>): Any? {
            return instance.my()
          }

          @Throws(Throwable::class)
          public override fun invoke(instance: MyConnector): Any? {
            return instance.my()
          }
        }

        public inner class MetaMyConnectorProxy : MetaProxy, MyConnector {
          private final val myInvocation: Function<Any?, Any?>

          public constructor(invocations: Map<MetaMethod<*>, Function<Any?, Any?>>) :
              super(invocations) {
            myInvocation = invocations[myMethod]!!
          }

          public override fun my(): MyCommunicator = myInvocation.apply(null) as MyCommunicator
        }
      }
    }

    public class MetaServicePackage : MetaPackage {
      private val myServiceClass: MetaMyServiceClass = register(MetaMyServiceClass())

      internal constructor() : super("service")

      public fun myServiceClass(): MetaMyServiceClass = myServiceClass

      public class MetaMyServiceClass : MetaClass<MyService> {
        private final val myMethodMethod: MetaMyMethodMethod = register(MetaMyMethodMethod())

        private final val getModelMethod: MetaGetModelMethod = register(MetaGetModelMethod())

        private final val compensationMethod: MetaCompensationMethod =
            register(MetaCompensationMethod())

        internal constructor() : super(metaType<MyService>(MyService::class.java))

        public fun myMethodMethod(): MetaMyMethodMethod = myMethodMethod

        public fun getModelMethod(): MetaGetModelMethod = getModelMethod

        public fun compensationMethod(): MetaCompensationMethod = compensationMethod

        public class MetaMyMethodMethod : StaticMetaMethod<Model> {
          private val modelParameter: MetaParameter<Model> = register(MetaParameter(0,
              "model",metaType<Model>(Model::class.java)))

          internal constructor() : super("myMethod",metaType<Model>(Model::class.java))

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.myMethod(arguments[0] as Model)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.myMethod(argument as Model)
          }

          public fun modelParameter(): MetaParameter<Model> = modelParameter
        }

        public class MetaGetModelMethod : StaticMetaMethod<Model> {
          internal constructor() : super("getModel",metaType<Model>(Model::class.java))

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.getModel()
          }

          @Throws(Throwable::class)
          public override fun invoke(): Any? {
            return MyService.getModel()
          }
        }

        public class MetaCompensationMethod : StaticMetaMethod<Mono<String>> {
          private val inputParameter: MetaParameter<Flux<String>> = register(MetaParameter(0,
              "input",metaType<Flux<String>>(Flux::class.java,metaType<String>(String::class.java))))

          internal constructor() :
              super("compensation",metaType<Mono<String>>(Mono::class.java,metaType<String>(String::class.java)))

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Any? {
            return MyService.compensation(arguments[0] as Flux<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Any? {
            return MyService.compensation(argument as Flux<String>)
          }

          public fun inputParameter(): MetaParameter<Flux<String>> = inputParameter
        }
      }
    }

    public class MetaModelPackage : MetaPackage {
      private val modelClass: MetaModelClass = register(MetaModelClass())

      internal constructor() : super("model")

      public fun modelClass(): MetaModelClass = modelClass

      public class MetaModelClass : MetaClass<Model> {
        private val `constructor`: MetaConstructorConstructor =
            register(MetaConstructorConstructor())

        private val valueField: MetaField<Sequence<String>> =
            register(MetaField("value",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java)),false))

        private final val getValueMethod: MetaGetValueMethod = register(MetaGetValueMethod())

        internal constructor() : super(metaType<Model>(Model::class.java))

        public fun `constructor`(): MetaConstructorConstructor = constructor

        public fun valueField(): MetaField<Sequence<String>> = valueField

        public fun getValueMethod(): MetaGetValueMethod = getValueMethod

        public class MetaConstructorConstructor : MetaConstructor<Model> {
          private val valueParameter: MetaParameter<Sequence<String>> = register(MetaParameter(0,
              "value",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java))))

          internal constructor() : super(metaType<Model>(Model::class.java))

          @Throws(Throwable::class)
          public override fun invoke(arguments: Array<Any>): Model {
            return Model(arguments[0] as Sequence<String>)
          }

          @Throws(Throwable::class)
          public override fun invoke(argument: Any): Model {
            return Model(argument as Sequence<String>)
          }

          public fun valueParameter(): MetaParameter<Sequence<String>> = valueParameter
        }

        public class MetaGetValueMethod : InstanceMetaMethod<Model, Sequence<String>> {
          internal constructor() :
              super("getValue",metaType<Sequence<String>>(Sequence::class.java,metaType<String>(String::class.java)))

          @Throws(Throwable::class)
          public override fun invoke(instance: Model): Any? = instance.`value`

          @Throws(Throwable::class)
          public override fun invoke(instance: Model, arguments: Array<Any>): Any? =
              instance.`value`
        }
      }
    }
  }
}
