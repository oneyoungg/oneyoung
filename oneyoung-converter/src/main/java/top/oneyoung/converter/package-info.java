/**
 * 这是一个基于Spring Bean 管理实现的Converter、Merger、Filler的功能。使用方法：
 * 1. 通常情况下，在各层之间转换的话，用Converter较多。
 * 2. 在数据库持久化时，需要将老数据和新数据合并，用Merger来实现。
 * 3. 在一些聚合模型中，通过Filler进行数据字段填充即可。
 * 4. 如果有遇到连续的流式操作，可以使用ConverterStream来串联起来这三个功能
 * 5. 需要通过com.alibaba.cz.base.tool.converter.factory.SpringFactoryRegister.FactoryConfiguration将相应的Factory注册到Spring中
 *
 * @author oneyoung
 * @since 2020/4/26
 */
package top.oneyoung.converter;