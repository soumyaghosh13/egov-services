update egasset_assetconfigurationvalues set value = '{"Fund":"01","Function":"000600"}' where keyid in (select id from egasset_assetconfiguration where keyname = 'DepreciationVoucherParams');