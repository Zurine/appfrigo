package es.smt.appfrigo.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.smt.appfrigo.bean.Data;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.Voucher;
import es.smt.appfrigo.model.ListDTO;
import es.smt.appfrigo.model.VoucherDTO;

public class VoucherConverter {

	private static VoucherConverter instance;
	
	public VoucherConverter(){}
	
	public static VoucherConverter getInstance()
	{
		if (instance == null )
		{
			instance = new VoucherConverter();
		}
		return instance;
	}
	
	public Voucher dtoToBean(VoucherDTO dto)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		Voucher v = new Voucher(dto.getId(), dto.getName(),dto.getDescription() ,new Image(dto.getImage()),
				df.format(new Date(dto.getStartDate())),  df.format(new Date(dto.getEndDate())), RetailConverter.getInstance().dtoToBean(dto.getRetail()), dto.getCode(), 
				dto.isActive(), dto.getShortName());
		v.setDescription2(dto.getDescription2());
		v.setDescription3(dto.getDescription3());
		
		return v;
	}
	
	public List<Voucher> dtoToBeanList(List<VoucherDTO> vouchers)
	{
		List<Voucher> list = new ArrayList<Voucher> ();
		for(VoucherDTO e: vouchers)
		{
			list.add(dtoToBean(e));
		}
		
		return list;
	}
	
	public VoucherDTO beanToDto(Voucher bean) throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		VoucherDTO dto = new VoucherDTO();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setDescription(bean.getDescription());
		dto.setCode(bean.getCode());
		dto.setStartDate(df.parse(bean.getStartDate()).getTime());
		dto.setEndDate(df.parse(bean.getEndDate()).getTime());
		dto.setActive(bean.isActive());
		dto.setImage(bean.getImage().getPath());
		dto.setDescription2(bean.getDescription2());
		dto.setDescription3(bean.getDescription3());
		dto.setShortName(bean.getShortName());
		
		return dto;
	}
	
	public Data dtoToBeanData(ListDTO data)
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Data result = new Data();
		result.setLabel(data.getLabel());
		if(data.getData()!=null && data.getData().size()>0)
		{
			result.setData("");
			for(String[] s:data.getData())
			{
				result.setData(result.getData()+" - " + s[0]);
			}
		}
		result.setDate( df.format(new Date(data.getDate())));
		
		return result;
	}
	
	public List<Data> dtoToBeanListData(List<ListDTO> vouchers)
	{
		List<Data> list = new ArrayList<Data> ();
		for(ListDTO e: vouchers)
		{
			list.add(dtoToBeanData(e));
		}
		
		return list;
	}
}
